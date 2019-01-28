/* Blink Example

   This example code is in the Public Domain (or CC0 licensed, at your option.)

   Unless required by applicable law or agreed to in writing, this
   software is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR
   CONDITIONS OF ANY KIND, either express or implied.
*/
#include <stdio.h>
#include "freertos/FreeRTOS.h"
#include "freertos/task.h"
#include "driver/gpio.h"
#include "sdkconfig.h"
#include "freertos/queue.h"
#include "string.h"
 #include "esp_log.h"
 #include "music.h"

extern xQueueHandle cmd_cmd_queue;




// #define BLINK_GPIO CONFIG_BLINK_GPIO

#define SCALE_MAX 8
unsigned char Scale_gpio[] = {0,13,12,14,27,26,25,33,32};
Music *current_music;
unsigned int scale_count=0;


void set_all_low(void)
{
    uint8_t i=1;
    for(i=1;i<SCALE_MAX+1;i++)
        gpio_set_level(Scale_gpio[i], 0);
}

void gpio_init(void)
{
    uint8_t i=0;
    for(i=1;i<SCALE_MAX+1;i++)
        gpio_pad_select_gpio(Scale_gpio[i]);

    for(i=1;i<SCALE_MAX+1;i++)    
        gpio_set_direction(Scale_gpio[i], GPIO_MODE_OUTPUT);

    set_all_low();
}

void play_init(void)
{
    current_music = duoremi;//fou;//ode_to_joy;//washer;//little_star;//washer;//one_summers_day;// doremi;//goose;
    scale_count = 7;//sizeof(duoremi) / sizeof(Music);//50;//30;//48;//42;//48;//14;//8;//18
}


// #define portMAX_DELAY ( TickType_t ) 0xffffffffUL
uint8_t tast_mode='2';
uint8_t tast_cmd=0;
uint8_t is_play=1;
uint8_t play_scale_index=0;
uint8_t play_mode=0;


void manual_play(uint8_t scale)
{
    uint8_t index = scale - '0';
    gpio_set_level(Scale_gpio[index], 1);
    vTaskDelay(100 / portTICK_PERIOD_MS);
    gpio_set_level(Scale_gpio[index], 0);
}


void play_music(void)
{
    gpio_set_level(Scale_gpio[current_music[play_scale_index].scale], 1);
    vTaskDelay(100 / portTICK_PERIOD_MS);
    gpio_set_level(Scale_gpio[current_music[play_scale_index].scale], 0);
    //vTaskDelay(current_music[i].last * 80  / portTICK_PERIOD_MS);
    switch(current_music[play_scale_index].beat)
    {
        case ONE:
            vTaskDelay(400  / portTICK_PERIOD_MS);
        break;
        case TWO:
            vTaskDelay(900  / portTICK_PERIOD_MS);
        break;
        case THR:
            vTaskDelay(1400  / portTICK_PERIOD_MS);
        break;
        case FOR:
            vTaskDelay(1900  / portTICK_PERIOD_MS);
        break;
    }
    play_scale_index ++;
    if(play_scale_index >= scale_count)
    {
        vTaskDelay(1000  / portTICK_PERIOD_MS);
        play_scale_index =0;
    }
}


void play(uint8_t cmd)
{
    switch(cmd)
    {
        //上一曲
        case '1':
        break;
        //下一曲
        case '2':
        break;
        //暂停/播放
        case '3':
            if(is_play)
                is_play = 0;
            else
                is_play=1;
        break;
        default:
        break;
    }
    if(is_play)
    {
        play_music();
    }
}


void blink_task(void *pvParameter)
{
    uint8_t  cmd_id[10];

    gpio_init();
    play_init();
    printf("The doremi's len is:%d\r\n",sizeof(duoremi) / sizeof(Music));
    while(1) {
        // 
        if(xQueueReceive(cmd_cmd_queue, cmd_id, 10/portTICK_PERIOD_MS)) {
            esp_log_buffer_char("GATTS_TABLE_DEMO,In Blink task",(char *)(cmd_id),strlen((char *)cmd_id));
            printf("The cmd is:%x,%x,%x,%x\r\n",cmd_id[0],cmd_id[1],cmd_id[2],cmd_id[3]);
            // free(cmd_id);
            if(cmd_id[0] == 's')
            {
                tast_mode = cmd_id[1];
                tast_cmd = cmd_id[2];
            }
        }

        switch(tast_mode)
        {
            //Manual play
            case '1':
                if(tast_cmd !=0)
                {
                manual_play(tast_cmd);
                tast_cmd = 0;
                }
            break;
            //auto play
            case '2':
                play(tast_cmd);
                tast_cmd=0;
            break;
            default:
            break;
        }
    }
}


#ifndef __MUSIC_H__
#define __MUSIC_H__

typedef struct {
    unsigned char scale;
    unsigned int beat;
} Music;


#define ONE 1
#define TWO 2
#define THR 3
#define FOR 4


Music duoremi[]={
    {1,ONE},
    {2,ONE},
    {3,ONE},
    {4,ONE},

    {5,ONE},
    {6,ONE},
    {7,THR},
};


Music one_summers_day[]=
{
    {3,ONE},
    {1,ONE},
    {5,ONE},
    {3,ONE},

    {2,ONE},
    {5,ONE},
    {2,ONE},

    {1,ONE},
    {6,ONE},
    {3,ONE},
    {1,ONE},

    {7,ONE},
    {1,ONE},
    {7,ONE},
};


Music washer[] = {
    {5,ONE},
    {3,ONE},
    {5,ONE},
    {3,ONE},

    {5,ONE},
    {3,ONE},
    {1,TWO},

    {2,ONE},
    {4,ONE},
    {3,ONE},
    {2,ONE},

    {5,FOR},

    {5,ONE},
    {3,ONE},
    {5,ONE},
    {3,ONE},

    {5,ONE},
    {3,ONE},
    {1,TWO},

    {2,ONE},
    {4,ONE},
    {3,ONE},
    {2,ONE},

    {1,FOR},

    {2,ONE},
    {2,ONE},
    {4,ONE},
    {4,ONE},

    {3,ONE},
    {1,TWO},
    {5,ONE},

    {2,ONE},
    {4,ONE},
    {3,ONE},
    {2,ONE},

    {5,FOR},

    {5,ONE},
    {3,ONE},
    {5,ONE},
    {3,ONE},

    {5,ONE},
    {3,ONE},
    {1,TWO},

    {2,ONE},
    {4,ONE},
    {3,ONE},
    {2,ONE},

    {1,FOR}
};

Music ode_to_joy[]={
    //
    {3,ONE},
    {3,ONE},
    {4,ONE},
    {5,ONE},

    {5,ONE},
    {4,ONE},
    {3,ONE},
    {2,ONE},    

    {1,ONE},
    {1,ONE},
    {2,ONE},
    {3,ONE},    

    {3,TWO},
    {2,ONE},
    {2,TWO},
    //
    {3,ONE},
    {3,ONE},
    {4,ONE},
    {5,ONE},

    {5,ONE},
    {4,ONE},
    {3,ONE},
    {2,ONE},    

    {1,ONE},
    {1,ONE},
    {2,ONE},
    {3,ONE},    

    {2,TWO},
    {1,ONE},
    {1,TWO},
};
//42
Music little_star[]={
    {1,ONE},
    {1,ONE},
    {5,ONE},
    {5,ONE},

    {6,ONE},
    {6,ONE},
    {5,TWO},

    {4,ONE},
    {4,ONE},
    {3,ONE},
    {3,ONE},

    {2,ONE},
    {2,ONE},
    {1,TWO}, 

    {5,ONE},
    {5,ONE},
    {4,ONE},
    {4,ONE},

    {3,ONE},
    {3,ONE},
    {2,TWO},

    {5,ONE},
    {5,ONE},
    {4,ONE},
    {4,ONE},

    {3,ONE},
    {3,ONE},
    {2,TWO},  

    {1,ONE},
    {1,ONE},
    {5,ONE},
    {5,ONE},

    {6,ONE},
    {6,ONE},
    {5,TWO},  

    {4,ONE},
    {4,ONE},
    {3,ONE},
    {3,ONE},

    {2,ONE},
    {2,ONE},
    {1,TWO}, 
};


Music goose[]=
{
    {5,ONE},
    {5,TWO},
    {5,THR},

    {5,ONE},
    {1,ONE},
    {3,ONE},
    {5,TWO},

    {5,THR}, 

    {6,ONE},
    {6,ONE},
    {5,ONE},
    {6,ONE},

    {3,THR}, 

    {2,ONE},
    {1,ONE},
    {2,ONE},
    {3,ONE},

    {2,THR}         
};


//50
Music fou[]={
    {3,ONE},
    {2,ONE},
    {3,ONE},
    {2,ONE},

    {3,ONE},
    {2,ONE},
    {3,ONE},
    {2,ONE},

    {3,ONE},
    {3,ONE},
    {2,TWO},

    {2,ONE},
    {1,TWO},
//
    {2,ONE},
    {1,ONE},
    {2,ONE},
    {1,ONE},

    {2,ONE},
    {1,ONE},
    {2,ONE},
    {1,ONE},

    {2,ONE},
    {2,ONE},
    {1,TWO},

    {1,ONE},
    {2,TWO},
    //
    {3,ONE},
    {2,ONE},
    {3,ONE},
    {2,ONE},

    {3,ONE},
    {2,ONE},
    {3,ONE},
    {2,ONE},

    {3,ONE},
    {3,ONE},
    {2,TWO},

    {2,ONE},
    {1,TWO},
    //
    {2,ONE},
    {1,ONE},
    {2,ONE},
    {1,ONE},

    {2,ONE},
    {1,ONE},
    {2,ONE},
    {1,ONE},

    {5,ONE},
    {5,ONE},
    {3,TWO},
};
#endif


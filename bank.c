
#include <string.h>
#include <stdio.h>
#include <stdlib.h> 

// Problem statement: https://open.kattis.com/problems/bank
// Azulia Yue Yang

int no_clients;
int time_to_close;

typedef struct amount_and_time{
    int waitTime;
    int amount;
    int taken;
}clientData;

clientData* data; 

//a greedy approach that finds the most amount of money that can be deposited starting at the latest time slot available
void find_best(int* time_slot){
    int total = 0;
    int max = 0;
    int index = -1;

    for(int i = time_to_close; i >= 0 ; i--){
        //printf("current time: %i ", i);
        for(int j =0; j < no_clients; j++){
                if(data[j].waitTime >= i){
                    //printf("\n amount: %i ", data[j].amount);
                    if(data[j].taken == -1 && data[j].amount > max){
                        //printf("max found");
                       // printf(" %i", data[j].amount);
                        max = data[j].amount;
                        index = j;
                    }
                }
            }
        total = total+max;
        if(index != -1 ){
            data[index].taken = 1;
        }
        max = 0; 
        index = -1;

    }

    printf("%i", total);

}

int main(int argc, char **argv){
    scanf("%i", &no_clients);
    scanf("%i", &time_to_close);

    data = malloc(sizeof(int)*3*(no_clients));

    int time_slot[time_to_close];
    int* time_ptr = time_slot;

    for (int i =0; i < no_clients; i++){
        scanf("%i", &data[i].amount);
        scanf("%i", &data[i].waitTime);
        data[i].taken = -1;
    }
    for(int  j =0; j < time_to_close; j++){
        time_slot[j] = 0;
    }

    find_best(time_ptr);

    free(data);

}
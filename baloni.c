#include <stdlib.h> 
#include <string.h>
#include <stdio.h>

int balloons;
int arrows = 0;
int max = 0; 
int max_index;
int current_height;
int balloons_hit = 0;

/**
* Problem statement: https://open.kattis.com/problems/baloni
* author: Yue Yang
*Idea: the arrow can only go down, not up
*so we want to to start by hitting the highest balloon first, then gradually move down
*/

int main(int argc, char *argv[]){

    scanf("%i", &balloons);
    int height[balloons];

    for (int i =0; i < balloons; i++){
        scanf("%i", &height[i]);
        if(height[i] > max){
            max_index = i; 
            max = height[i];
        }
    }

    //initialize data assuming we hit the first (highest) balloon
    current_height = max-1;
    height[max_index] = -1; 
    balloons_hit++;
    arrows++;

    for(int j = max_index+1; j < balloons; j++){
        if(height[j] == current_height){
            height[j] = -1;
            current_height--;
            balloons_hit++;
        }
    }

    max = 0;

    //stop until we hit all balloons
    while(balloons_hit < balloons){
        for(int j = 0; j < balloons; j++){
            if(height[j] > max){
                max_index = j; 
                max = height[j];
            }
        }

        //printf("Max: %i\n", height[max]);
        current_height = max-1;
        balloons_hit++;
        height[max_index] = -1; 
        arrows++;
       // printf("Arrows: %i\n", arrows);
       // printf("balloons: %i\n", balloons_hit);
    
        for(int j = max_index+1; j < balloons; j++){
            if(height[j] == current_height){
                height[j] = -1;
                current_height--;
                balloons_hit++;
            }
        }
        max = 0;
    }

    printf("%i", arrows);
}

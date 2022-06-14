#include <bits/stdc++.h>

using namespace std;

pair <float, long long> pp[90];

const int n = 90;
const int m = 26;
const float MAX_HEIGHT = 2852;
const float MIN_HEIGHT = 2800;

int mark[n];
int page[m+1];

void output(){
    for (int i = 1; i <= m; i++){
        for (int j = 0; j < n; j++){
            if (mark[j] == i){
                cout<<"Page "<<i<<": "<<pp[j].second<<" "<<pp[j].first<<endl;
            }
        }
    }
    exit(0);
}

bool filledAll(int *mark){
    for (int i = 0; i < n; i++)
        if (mark[i] == 0){
            return false;
        }
    return true;
}

void check(){
    if (filledAll(mark)) {
        output();
    }
    else{
        return;
    }
}
void fill1(pair <float, long long> *pp, int p){
    // cout<<"solving..."<<endl;
    if (p == m+1){//
        check();
        return;
    }

    bool f = false; //flag variable
    // back-tracking
    for (int i = n-1; i >= 0; i--){
        if (mark[i] == 0){
            if (page[p] + pp[i].first <= MAX_HEIGHT){
                f = true;
                page[p] += pp[i].first;
                mark[i] = p;
                
                if (page[p] < MAX_HEIGHT){
                    // continue fill in current page
                    fill1(pp, p);
                }
                else{
                    // next page
                    fill1(pp, p+1);
                }
                page[p] -= pp[i].first;

                // page ~~ queue
                if (page[p] == 0) {
                    cout<<"UNSOLVED!";
                    exit(0);
                }

                mark[i] = 0;
            }
        }
    }

    // no question can be filled
    if (f == false){
        
        // all question is filled
        if (filledAll(mark) == true){
            output();
        }
        else{ // or exit question isnt filled
            if (page[p] < MIN_HEIGHT) return;
            else {
                fill1(pp, p+1);
            }
        }   
    }
}



int main() {

    freopen("quetions.txt", "r", stdin);
    int index = 0;

    int t = 0;
    while (cin){
        cin>>pp[index].second>>pp[index].first;
        t += pp[index].first;
        index++;
    }

    // pre-process
    if (t > m * MAX_HEIGHT) {
        cout<<"UNSOLVED";
        exit(0);
    }
    
    for (int i = 0; i < n; i++) mark[i] = 0;

    sort(pp+0,pp+n);

    for (int i = 0; i < 2; i++){
        fill1(pp, 1);   
    }

    cout<<"UNSOLVED";
}
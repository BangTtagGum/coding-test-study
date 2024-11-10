#include <iostream>
#include <algorithm>
#include <deque>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define loopj for (int j = 1; j <= n; j ++)
#define loopjr for (int j = n; j >= 1; j --)
#define loopi for (int i = 1; i <= n; i ++)
#define loopir for (int i = n; i >= 1; i --)   
using namespace std;

int n;
int state[21][21];
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};
int ans;
deque<int> dq;

void input(){
    fastio
    cin >> n;
    loopi {
        loopj {
            cin >> state[i][j];
            //ans = max(ans, state[i][j]);
        }
    }
}

void show(){
    cout << '\n';
    loopi {
        loopj {
            cout << state[i][j] << ' ';
        }
        cout << '\n';
    }
}

void copy_state(int m1[21][21], int m2[21][21]){
    loopi {
        loopj {
            m2[i][j] = m1[i][j];
        }
    }
}

void deque_push(int i, int j){
    if (state[i][j] == 0) return;

    dq.push_back(state[i][j]);
    state[i][j] = 0;
}

void deque_pop(int i, int j){
    if (dq.empty()) return;

    state[i][j] = dq.front();
    dq.pop_front();

    if (dq.empty()) return;
    
    if (state[i][j] == dq.front()){
        state[i][j] += dq.front();
        dq.pop_front();
    }
}

void compare(){
    loopi {
        loopj {
            ans = max(ans, state[i][j]);
        }
    }
}

void move(int dir){
    
    if (dir == 0) {
        loopj {
            loopir deque_push(i, j); 
            loopir deque_pop(i, j);
        }
        compare();
    }
    else if (dir == 1){
        loopj {
            loopi deque_push(i, j);
            loopi deque_pop(i, j);       
        }
        compare();
    }
    else if (dir == 2){
        loopi {
            loopjr deque_push(i, j);
            loopjr deque_pop(i, j);
        }
        compare();
    }
    else if (dir == 3){
        loopi {
            loopj deque_push(i, j);
            loopj deque_pop(i, j);
        }
        compare();
    }
}

void four_dir_check(int cnt){

    if (cnt == 5) return;

    int state_copied[21][21];

    copy_state(state, state_copied);

    for (int dir = 0; dir < 4; dir ++){ 
        move(dir);
        //show();
        four_dir_check(cnt + 1);
        copy_state(state_copied, state);
    }
}

int main(){
    input();
    four_dir_check(0);
    cout << ans;
    return 0;
}
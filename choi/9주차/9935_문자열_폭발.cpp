#include <iostream>
#include <algorithm>
#include <deque>
#include <string>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

string str, bomb;
deque<char> dq;

using namespace std;

void input(){
    fastio
    cin >> str >> bomb;
}

void solve(){
    int i, j, len = bomb.length();
    for (i = 0; i < str.size(); i ++) {
        dq.push_back(str[i]);
        if (dq.back() != bomb[len - 1]) continue;
        if (dq.size() < len) continue;
        for (j = 0; j < len; j ++) 
            if (dq[dq.size() - 1 - j] != bomb[len - 1 - j]) break;
        if (j != len) continue;
        for (j = 0; j < len; j ++) 
            dq.pop_back();
    }

    if (dq.size())
        for (char tmp : dq) cout << tmp;
    else 
        cout << "FRULA";
}

int main(){
    input();
    solve();
    return 0;
}
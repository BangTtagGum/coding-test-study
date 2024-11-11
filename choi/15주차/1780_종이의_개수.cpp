#include <iostream>
#include <algorithm>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define MAX (3*3*3*3*3*3*3)
using namespace std;

int n;
int map[MAX + 1][MAX + 1];

int cnt1, cnt2, cnt3;

void input(){
    fastio
    cin >> n;
    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= n; j ++){
            cin >> map[i][j];
        }
    }
}

int solve(int i, int j, int s){

    int a, b, c, ni, nj, ns;

    if (s == 1) return map[i][j];

    a = b = c = 0;
    ns = s / 3, ni = i + s, nj = j + s;

    for (int x = i; x < ni; x += ns){
        for (int y = j; y < nj; y += ns){
            int tmp = solve(x, y, ns);
            if (tmp == -1) a ++;
            else if (tmp == 0) b ++;
            else if (tmp == 1) c ++;
        } 
    }

    if (a == 9) return -1;
    if (b == 9) return 0;
    if (c == 9) return 1;

    cnt1 += a, cnt2 += b, cnt3 += c;
    return MAX;
}

int main(){
    input();
    int ans;
    if ((ans = solve(1, 1, n)) != MAX) {
        if (ans == -1) cnt1 = 1;
        else if (ans == 0) cnt2 = 1;
        else cnt3 = 1;
    }
    cout << cnt1 << '\n' << cnt2 << '\n' << cnt3;
    return 0;
}



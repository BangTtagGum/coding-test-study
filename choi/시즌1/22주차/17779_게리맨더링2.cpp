#include <iostream>
#include <algorithm>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define con1 i >= 1 && i < x + d1 && j >= 1 && j <= y && i + j < x + y
#define con2 i >= 1 && i <= x + d2 && j > y && j <= n && j - i > y - x
#define con3 i >= x + d1 && i <= n && j >= 1 && j < y - d1 + d2 && i - j > (x + d1 + d2) - (y + d2 - d1)
#define con4 i > x + d2 && i <= n && j >= y - d1 + d2 && j <= n && i + j > (x + d1 + d2) + (y + d2 - d1)
using namespace std;

int n;
int map[21][21];
// int dx[] = {1, -1, 1, -1}, dy[] = {1, 1, -1, -1};
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};
int sum, ans;

void input(){
    fastio
    cin >> n;
    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= n; j ++){
            cin >> map[i][j];
            sum += map[i][j];
        }
    }
}

void calculate(int x, int y, int d1, int d2){
    
    int mx = 0, mn = 44444;
    int sums[5] = {0,};

    for (int i = 1; i <= n; i ++){
        for (int j = 1; j <= n; j ++){
            if (con1) sums[0] += map[i][j];
            else if (con2) sums[1] += map[i][j];
            else if (con3) sums[2] += map[i][j];
            else if (con4) sums[3] += map[i][j];
        }
    }
    
    sums[4] = sum - (sums[0] + sums[1] + sums[2] + sums[3]);

    for (int i = 0; i < 5; i ++){
        mx = max(mx, sums[i]);
        mn = min(mn, sums[i]);
    }
    ans = min(ans, mx - mn);
}

void solve(){
    ans = 44444;
    for (int x = 1; x <= n; x ++){
        for (int y = 1; y <= n; y ++){
            for (int d1 = 1; d1 <= y - 1; d1 ++){
                for (int d2 = 1; d2 <= n - y; d2 ++){
                    if (x + d1 + d2 <= n) {

                        // cout << x << ' ' << y << ' ' << d1 << ' ' << d2 << ' ';
                        calculate(x, y, d1, d2);
                    }
                }
            }
        }
    }
    cout << ans;
}

int main(){
    input();
    solve();
    return 0;
}
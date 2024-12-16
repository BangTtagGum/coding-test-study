#include <iostream>
#include <algorithm>
#include <vector>
#include <iomanip>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;
using ll = long long;

int n;
ll d;
ll pos[1000001];

void init(){
  cin >> n >> d;
  for (int i = 1; i <= n; ++i)
    cin >> pos[i];
}

ll getEvenMove(){
  ll maxD = -1e16, minD = 1e16;
  for (int i = 1; i <= n; ++i){
    maxD = max(maxD, (i - 1) * d - pos[i]);
    minD = min(minD, (i - 1) * d - pos[i]);
  }
  return maxD - minD;
}

void solve(){
  ll ret1 = getEvenMove();
  d *= -1;
  ll ret2 = getEvenMove();

  ll answer = min(ret1, ret2);
  cout << answer / 2 << '.' << answer % 2 * 5;
}

int main(){
  fastio
  init();
  solve();
  return 0;
}
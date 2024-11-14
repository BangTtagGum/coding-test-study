#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
using namespace std;

int n;

void init(){
  cin >> n;
}

bool check(){
  int a, b;

  a = 0;

  for (int i = 1; i < n; ++i){
    cin >> b;
    if (a){
      if (b == 0 || b == 2) b = 1;
      else if (b == 1) b = 0;
      else return false;
    }
    else {
      if (b >= 2) return false;
    }
    a = b;
  }

  cin >> b;
  return ((a && b == 1) || (!a && b == 0));
}

void solve(){
  check() ? cout << "YES" : cout << "NO";
}

int main(){
  fastio
  init();
  solve();
  return 0;
}
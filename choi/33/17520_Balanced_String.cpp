#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define DIV 16769023
using namespace std;

int n;

void init(){
  cin >> n;
}

void solve(){ 
  long long answer = 1;
  for (int i = 1; i <= (n + 1) / 2; ++i)
    answer = (answer * 2) % DIV;
  cout << answer;
}

int main(){
  fastio
  init();
  solve();
  return 0;
}
#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

vector<int> dices[2];

void input(){
  for (int i = 0; i < 2; ++i){
    dices[i].resize(6);
    for (int j = 0; j < 6; ++j){
      cin >> dices[i][j];
    } 
  }
}

void solve(){
  int win = 0, total = 36;

  for (int i = 0; i < 6; ++i){
    for (int j = 0; j < 6; ++j){
      if (dices[0][i] > dices[1][j]) ++win;
    }
  }

  bool haveFactor = 1;
  while (haveFactor){
    haveFactor = 0;
    if (win % 2 == 0 && total % 2 == 0){
      haveFactor = 1;
      win /= 2;
      total /= 2;
    }
    if (win % 3 == 0 && total % 3 == 0){
      haveFactor = 1;
      win /= 3;
      total /= 3;
    }
  }

  cout << win << '/' << total;
}

int main(){
  fastio
  input();
  solve();
  return 0;
}
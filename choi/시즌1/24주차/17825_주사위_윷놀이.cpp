#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
#define START 0
#define END 21
#define CENTER 22
using namespace std;

// 21 + 11
int dice[10];
int score[33];
bool isPortal[33];
int linkedPortal[33];
vector<int> horseOnBoard(33, -1);
int positions[4];
int answer;

void input(){
  for (int r = 0; r < 10; ++r)
    cin >> dice[r];
}

int connectPortal(int start, int len, int diff, int idx){
  int portal = start;
  isPortal[portal] = 1;

  for (int l = 0; l < len; ++l){
    linkedPortal[portal] = ++idx;
    isPortal[idx] = 1;
    score[idx] = score[portal] + diff;
    portal = idx;
  }
  return idx;
}

void setBoard(){
  int idx;
  for (int s = 0, idx = 0; idx < 21; ++idx, s = s + 2)
    score[idx] = s; 
  
  idx = CENTER;

  idx = connectPortal(5, 3, 3, idx);
  linkedPortal[idx] = CENTER;

  idx = connectPortal(10, 2, 2, idx);
  linkedPortal[idx] = CENTER;

  isPortal[15] = 1;
  linkedPortal[15] = ++idx;
  score[idx] = 28;
  idx = connectPortal(idx, 2, -1, idx);
  linkedPortal[idx] = CENTER;

  score[CENTER] = 25;
  idx = connectPortal(CENTER, 2, 5, idx);
  linkedPortal[idx] = 20;
}

int usePortal(int start, int cnt){
  int pos = start;
  while (cnt && isPortal[pos]){
    pos = linkedPortal[pos];
    --cnt;
  }
  if (cnt) return min(pos + cnt, END);
  return pos;
}

void selectHorse(int depth, int sumScore){
  if (depth == 10){
    answer = max(answer, sumScore);
    return;
  }

  for (int horse = 0; horse < 4; ++horse){
    int cur = positions[horse];
    if (cur == END) 
      continue;
    int nxt;
    if (isPortal[cur])
      nxt = usePortal(cur, dice[depth]);
    else {
      nxt = cur + dice[depth];
      nxt = min(nxt, END);
    }
    if (nxt != END && horseOnBoard[nxt] != -1)
      continue;

    int status = horseOnBoard[nxt];
    
    positions[horse] = nxt;
    horseOnBoard[cur] = -1;
    horseOnBoard[nxt] = horse;
    selectHorse(depth + 1, sumScore + score[nxt]);
    positions[horse] = cur;
    horseOnBoard[cur] = horse;
    horseOnBoard[nxt] = status;
  }
}

void solve(){
  setBoard();
  selectHorse(0, 0);
  cout << answer;
}

int main(){
  fastio
  input();
  solve();
  return 0;
}
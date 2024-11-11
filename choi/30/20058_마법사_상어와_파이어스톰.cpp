#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#include <cmath>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int N, Q, L;
int ice[65][65], tmp[65][65];
int dr[] = {1, -1, 0, 0}, dc[] = {0, 0, 1, -1};

void input(){
  cin >> N >> Q;
  N = pow(2, N);
  for (int r = 1; r <= N; ++r){
    for (int c = 1; c <= N; ++c){
      cin >> ice[r][c];
    }
  }
}

void show(){
  for (int i = 1; i <= N; ++i){
    for (int j = 1; j <= N; ++j){
      cout << ice[i][j] << ' ';
    }
    cout << endl;
  }
  cout << endl;
}

// 00<->03, 01<->13, 23->33->32, 42->21 31->10->31
void rotateSubArr(int i, int j){

  for (int dr = 0; dr < L; ++dr){
    for (int dc = 0; dc < L; ++dc){
      tmp[i + dr][j + dc] = ice[i + (L - 1 - dc)][j + dr];
    }
  }
  for (int dr = 0; dr < L; ++dr){
    for (int dc = 0; dc < L; ++dc){
      ice[i + dr][j + dc] = tmp[i + dr][j + dc];
    }
  }
}

void firestorm(){
  for (int r = 1; r <= N; r = r + L){
    for (int c = 1; c <= N; c = c + L){
      rotateSubArr(r, c);
    }
  }
}

bool haveIce(int r, int c){
  if (r < 1 || r > N || c < 1 || c > N) return false;
  return tmp[r][c];
}

void meltIce(){
  for (int r = 1; r <= N; ++r){
    for (int c = 1; c <= N; ++c){
      if (!ice[r][c]) continue;

      int cnt = 0;
      for (int d = 0; d < 4; ++d)
        cnt += haveIce(r + dr[d], c + dc[d]);
      if (cnt < 3)
        ice[r][c]--;
    }
  }
}

int getSumIce(){
  int sum = 0;
  for (int r = 1; r <= N; ++r){
    for (int c = 1; c <= N; ++c){
      sum += ice[r][c];
    }
  }
  return sum;
}

int getArea(int i, int j, vector<vector<bool>> &isVisited){
  queue<pair<int, int>> q;

  int area = 1;
  q.push({i, j});
  isVisited[i][j] = 1;

  while (q.size()){
    auto [r, c] = q.front();
    q.pop();

    for (int d = 0; d < 4; ++d){
      int nr = r + dr[d];
      int nc = c + dc[d];
      if (nr < 1 || nr > N || nc < 1 || nc > N) continue;
      if (!ice[nr][nc]) continue;
      if (isVisited[nr][nc]) continue;

      ++area;
      q.push({nr, nc});
      isVisited[nr][nc] = 1;
    }
  }

  return area;
}

int getMaxArea(){
  vector<vector<bool>> isVisited;
  isVisited.assign(N + 1, vector<bool>(N + 1, 0));
  
  int maxArea = 0;
  for (int r = 1; r <= N; ++r){
    for (int c = 1; c <= N; ++c){
      if (!ice[r][c]) continue;
      if (isVisited[r][c]) continue;
      maxArea = max(maxArea, getArea(r, c, isVisited));
    }
  }
  return maxArea;
}

void solve(){
  while (Q--){
    cin >> L;
    L = pow(2, L);
    firestorm();
    // show();
    meltIce();
    // show();
  }

  cout << getSumIce();
  cout << '\n';
  cout << getMaxArea();
}

int main(){
  fastio
  input();
  solve();
  return 0;
}
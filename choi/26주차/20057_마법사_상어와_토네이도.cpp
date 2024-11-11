#include <iostream>
#include <algorithm>
#include <vector>
#include <cstdio>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int N;
int sands[500][500];
int dr[] = {0, 1, 1, 1, 0, -1, -1, -1};
int dc[] = {-1, -1, 0, 1, 1, 1, 0, -1};
int answer;

void input(){
  cin >> N;
  for (int r = 1; r <= N; ++r){
    for (int c = 1; c <= N; ++c){
      cin >> sands[r][c];
    }
  }
}

bool isWithinRange(int r, int c){
  return !(r < 1 || r > N || c < 1 || c > N);
}

void moveSand(int r, int c, int nr, int nc, float sand){
  sand = (int)sand;
  isWithinRange(nr, nc) ? sands[nr][nc] += sand : answer += sand;
  sands[r][c] -= sand;
}

void move(int &r, int &c, int s, int d){
  int nr, nc;
  while (s--){
    r += dr[d];
    c += dc[d];
    float sand = (float)sands[r][c];

    moveSand(r, c, r + 2 * dr[d], c + 2 * dc[d], sand * 0.05);

    moveSand(r, c, r + dr[(d + 1) % 8], c + dc[(d + 1) % 8], sand * 0.1);
    moveSand(r, c, r + dr[(d + 2) % 8], c + dc[(d + 2) % 8], sand * 0.07);
    moveSand(r, c, r + 2 * dr[(d + 2) % 8], c + 2 * dc[(d + 2) % 8], sand * 0.02);
    moveSand(r, c, r + dr[(d + 3) % 8], c + dc[(d + 3) % 8], sand * 0.01);

    moveSand(r, c, r + dr[(d + 7) % 8], c + dc[(d + 7) % 8], sand * 0.1);
    moveSand(r, c, r + dr[(d + 6) % 8], c + dc[(d + 6) % 8], sand * 0.07);
    moveSand(r, c, r + 2 * dr[(d + 6) % 8], c + 2 * dc[(d + 6) % 8], sand * 0.02);
    moveSand(r, c, r + dr[(d + 5) % 8], c + dc[(d + 5) % 8], sand * 0.01);

    moveSand(r, c, r + dr[d], c + dc[d], sands[r][c]);

    // show();
  }
}

void solve(){
  int r, c;
  int s = 1, d = 0;
  r = c = (N + 1) / 2;
  while (s < N){
    move(r, c, s, d);
    d = (d + 2) % 8;
    move(r, c, s, d);
    d = (d + 2) % 8;
    ++s;
  }
  move(r, c, s - 1, d);
  
  cout << answer;
}

int main(){
  fastio
  input();
  solve();
  return 0;
}
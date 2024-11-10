#include <iostream>
#include <algorithm>
#include <vector>
#include <queue>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int N, K;
vector<int> A;
int head, tail;
queue<int> robots;
vector<bool> haveRobot;
int numBroken;

void input(){
  cin >> N >> K;
  A.resize(2 * N);
  for (int i = 0; i < 2 * N; ++i)
    cin >> A[i];
}

void rotateRail(){
  head = (head + 2 * N - 1) % (2 * N);
  tail = (head + N - 1) % (2 * N);
  if (robots.size() && robots.front() == tail){
    robots.pop();
    haveRobot[tail] = 0;
  }
}

bool moveRobots(){
  int cnt = robots.size();
  while (cnt--){
    int cur = robots.front();
    int nxt = (cur + 1) % (2 * N);
    robots.pop();

    if (!haveRobot[nxt] && A[nxt]){
      if (--A[nxt] == 0 && ++numBroken == K)
        return true;
      haveRobot[cur] = 0;
      if (nxt != tail) {
        haveRobot[nxt] = 1;
        robots.push(nxt);
      }
    }
    else {
      robots.push(cur);
    }
  } 
  return false;
}

bool putRobot(){
  if (!A[head]) return false;
  if (--A[head] == 0 && ++numBroken == K)
    return true;
  haveRobot[head] = 1;
  robots.push(head);
  return false;
}

void solve(){
  haveRobot.assign(2 * N, 0);
  int turn = 0;
  while (1){
    ++turn;
    rotateRail();
    if (moveRobots() || putRobot()) break;
  }
  cout << turn;
}

int main(){
  fastio
  input();
  solve();
  return 0;
}
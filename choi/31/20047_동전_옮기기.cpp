#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int n;
string S, T;
char a, b;
vector<int> horses;
int dp[10000][3];

void input(){
  int i, j;
  cin >> n >> S >> T >> i >> j;
  horses.assign({S[i], S[j]});
  S = S.substr(0, i) + S.substr(i + 1, j - i - 1) + S.substr(j + 1);
}

int findMatch(int s, int t, int numPlaced){
  if (t == n) return 0;
  
  if (S[s] == T[t]) {
    dp[t][numPlaced] = max(dp[t][numPlaced], findMatch(s + 1, t + 1, numPlaced));
    if (numPlaced <= 1 && T[t] == horses[numPlaced])
      dp[t][numPlaced] = max(dp[t][numPlaced], findMatch(s, t + 1, numPlaced + 1));
    return dp[t][numPlaced] + 1;
  }
  else {
    if (numPlaced == 2) return 0;
    if (T[t] != horses[numPlaced]) return 0;
    return (dp[t][numPlaced] = max(dp[t][numPlaced], findMatch(s, t + 1, numPlaced + 1))) + 1;
  }
}

void solve(){
  findMatch(0, 0, 0) == n ? cout << "YES" : cout << "NO";
  cout << '\n';
}

int main(){
  fastio
  input();
  solve();
  return 0;
}
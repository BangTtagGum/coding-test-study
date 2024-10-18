#include<iostream>
#include <queue>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);
using namespace std;

int N, M;
vector<vector<int>> color;
int di[] = {1, -1, 0, 0}, dj[] = {0, 0, 1, -1};
int answer;

void input(){
    cin >> N;
    M = 3 * N;
    color.resize(M, vector<int>(N));
    for (int i = 0; i < M; ++i){
        for (int j = 0; j < N; ++j){
            cin >> color[i][j];
        }
    }
}

bool isValid(int i, int j, vector<vector<int>> &visited){
    if (i < 0 || i >= N || j < 0 || j >= N)
        return false;
    if (!color[i + 2 * N][j])
        return false;
    return visited[i][j] == 0;
}

int checkConnection(int i, int j, int &top, int &bottom, int &left, int &right, int visitCnt, vector<vector<int>> &visited){
    int cnt = 1;
    queue<pair<int, int>> q;
    
    q.push({i, j});
    visited[i][j] = visitCnt;

    while (q.size()){
        auto [i, j] = q.front();
        q.pop();

        for (int d = 0; d < 4; ++d){
            int ni = i + di[d];
            int nj = j + dj[d];
            if (isValid(ni, nj, visited) && color[i + 2 * N][j] == color[ni + 2 * N][nj]){
                ++cnt;
                top = min(top, ni);
                bottom = max(bottom, ni);
                left = min(left, nj);
                right = max(right, nj);
                
                q.push({ni, nj});
                visited[ni][nj] = visitCnt;
            }
        }
    }
    return cnt;
}

int explodeCars(int i, int j, int visitCnt, vector<vector<int>> &visited){
    int top = i, bottom = i, left = j, right = j;
    int cnt = checkConnection(i, j, top, bottom, left, right, visitCnt, visited);
    
    for (int j = left; j <= right; ++j){
        int fall = 0;
        for (int i = bottom; i >= top; --i){
            if (visited[i][j] == visitCnt) {
                ++fall;
            }
            else if (fall){
                color[i + 2 * N + fall][j] = color[i + 2 * N][j];
                color[i + 2 * N][j] = 0;
            }
        }

        if (!fall) continue;
        for (int i = top - 1 + 2 * N; i >= 0 && color[i][j]; --i){
            color[i + fall][j] = color[i][j];
            color[i][j] = 0;
        }
    }
    return cnt + (right - left + 1) * (bottom - top + 1);
}

int pretendExplodeCars(int i, int j, int visitCnt, vector<vector<int>> &visited){
    int top = i, bottom = i, left = j, right = j;
    int cnt = checkConnection(i, j, top, bottom, left, right, visitCnt, visited);
    
    return cnt + (right - left + 1) * (bottom - top + 1);
}

void finalBacktrack(int turn, int sumScore){
    if (turn > 3){
        answer = max(answer, sumScore);
        return;
    }

    int visitCnt = 0;
    vector<vector<int>> visited(N, vector<int>(N, 0));
    
    for (int j = 0; j < N; ++j){
        for (int i = N - 1; i >= 0 && color[i + 2 * N][j]; --i){
            if (visited[i][j]) continue;
            int score = pretendExplodeCars(i, j, ++visitCnt, visited);
            finalBacktrack(turn + 1, sumScore + score);
        }
    }  
}

void backtrack(int turn, int sumScore){
    int visitCnt = 0;
    vector<vector<int>> visited(N, vector<int>(N, 0));
    auto colorCopy = color;
    
    for (int j = 0; j < N; ++j){
        for (int i = N - 1; i >= 0 && color[i + 2 * N][j]; --i){
            if (visited[i][j]) continue;
            int score = explodeCars(i, j, ++visitCnt, visited);
            if (turn == 2)
                finalBacktrack(turn + 1, sumScore + score);
            else
                backtrack(turn + 1, sumScore + score);
            color = colorCopy;
        }
    }
}

void solve(){
    backtrack(1, 0);
    cout << answer;
}

int main(int argc, char** argv)
{
     fastio
    input();
    solve();
   return 0;
}
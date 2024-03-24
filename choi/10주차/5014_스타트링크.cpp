#include <iostream>
#include <queue>

using namespace std;

int f, s, g, u, d;
int visited[1000001];

void bfs(){
    queue<int> q;

    visited[s] = 1;
    q.push(s);

    while(q.size()){
        int x = q.front();
        q.pop();

        int y;
        for (int i=0; i<2; i++){
            if (i) y = x + u;
            else y = x - d;

            if (y < 1 || y > f) continue;
            if (visited[y]) continue;
            visited[y] = visited[x] + 1;
            q.push(y);
        }
    }

}

int main(){
    ios::sync_with_stdio(false);
    
    cin >> f >> s >> g >> u >> d;

    bfs();
    visited[g]? cout << visited[g]-1 : cout << "use the stairs";
}
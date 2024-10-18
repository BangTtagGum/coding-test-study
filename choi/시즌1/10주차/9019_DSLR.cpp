#include <iostream>
#include <queue>
#include <stack>
#include <string>

using namespace std;

int T, A, B;
int proto[10000];
char order[10000], dslr[] = {'D', 'S', 'L', 'R'};

int calculate (int op, int num){
    if (op == 0) return (num * 2) % 10000;
    if (op == 1) return num? num - 1 : 9999;
    if (op == 2) return num / 1000 + num % 1000 * 10;
    if (op == 3) return num / 10 + num % 10 * 1000;
}

void print(int num){
    if(order[num] == -1) return;
    print(proto[num]);
    cout << order[num];
}

void bfs(){

    queue<int> q;
    order[A] = -1;
    q.push(A);

    while(q.size()){
        auto u = q.front();
        q.pop();
        
        if (u == B) {
            return;
        }

        for (int d = 0 ; d < 4 ; d ++){
            int v = calculate(d, u);
            if (order[v]) continue;
            proto[v] = u;
            order[v] = dslr[d];
            q.push(v);
        }
    }
}

int main(){

    ios::sync_with_stdio(false);
    cin.tie(0); 
    cout.tie(0);

    cin >> T;
    while (T --){
        cin >> A >> B;
        for (int i=0; i<=9999; i++) order[i] = 0;
        bfs();
        print(B);
        cout << "\n";
    }
    
}

#include <iostream>
#include <stack>

using namespace std;

typedef pair<int, int> p;

int n,tmp,idx;
stack<p> st;

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cin.tie(0);
    cin>>n;
    for(int i=1;i<=n;i++){
        cin>>tmp;
        while(!st.empty() && st.top().first<=tmp){
            st.pop();
        }
        cout<<(st.empty()? 0: st.top().second)<<' ';
        st.push({tmp,i});
    }
}
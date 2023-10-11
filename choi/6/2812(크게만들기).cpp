#include <iostream>
#include <deque>

using namespace std;

int n,k;
char ch;
deque<char> st;

int main(){
    cin>>n>>k;
    int cnt=k;
    for(int i=1;i<=n;i++){
        cin>>ch;
        while(cnt && !st.empty() && ch>st.back()){
            st.pop_back();
            cnt--;
        }
        if(st.size()<n-k)
            st.push_back(ch);
    }
    for(char ch:st){
        cout<<ch;
    }
}
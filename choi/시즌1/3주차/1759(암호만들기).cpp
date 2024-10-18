#include <iostream>
#include <algorithm>
#include <vector>

using namespace std;

int l,c,m,j;
char arr[16];
vector<char> str;

void dfs(int u){
    if(str.size()==l){
        if(m>0 && j>1){
            for(char ch:str) cout<<ch;
            cout<<'\n';
        }
        return;
    }
    for(int v=u; v<=c;v++){
        char tmp=arr[v];
        str.push_back(tmp);
        if(tmp=='a' || tmp=='e' || tmp=='i' || tmp=='o' || tmp=='u')
            m++;
        else j++;
        dfs(v+1);
        tmp=str.back();
        str.pop_back();
        if(tmp=='a' || tmp=='e' || tmp=='i' || tmp=='o' || tmp=='u')
            m--;
        else j--;
    }       
    
}

int main(){
    ios::sync_with_stdio(false);
    cin.tie(0); cout.tie(0);
    cin>>l>>c;
    //int tmp;
    for(int i=1;i<=c;i++){
        cin>>arr[i];
    }
    sort(arr+1,arr+c+1);
    dfs(1);
    
}
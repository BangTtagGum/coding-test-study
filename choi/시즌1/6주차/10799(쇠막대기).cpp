#include <iostream>
#include <stack>

using namespace std;

stack<char> st;
int cnt,sum;

int main(){

    char ch;
    while((ch=cin.get())!='\n'){
        st.push(ch);
    }

    while(!st.empty()){
        if(st.top()==')'){      //막대기 층++
            cnt++;
            st.pop();
            if(st.top()=='('){  //알고보니 대포였음
                cnt--;
                st.pop();
                sum+=cnt;
            }
        }
        else{                   //막대기 층--
            cnt--;
            st.pop();
            sum++;
        }
    }

    cout<<sum;
    return 0;

}
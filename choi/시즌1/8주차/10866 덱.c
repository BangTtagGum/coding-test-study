#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct node
{
    int data;
    struct node* prev;
    struct node* next;
}Node;
typedef struct dq
{
    Node* head;
    Node* tail;
    int numofdata;
}Deque;

void dequeinit(Deque* pdq)
{
    pdq->head = NULL;
    pdq->tail = NULL;
    pdq->numofdata = 0;
}
int size(Deque* pdq)
{
    return pdq->numofdata;
}
int empty(Deque* pdq)
{
    return (pdq->numofdata==0);
}
void push_front(Deque* pdq, int data)
{
    Node* newnode = (Node*)malloc(sizeof(Node));
    newnode->data = data;

    newnode->next = pdq->head;
    
    if (empty(pdq))
        pdq->tail = newnode;
    else
        pdq->head->prev = newnode;

    newnode->prev = NULL;
    pdq->head = newnode;

    (pdq->numofdata)++;

}
void push_back(Deque* pdq, int data)
{
    Node* newnode = (Node*)malloc(sizeof(Node));
    newnode->data = data;
    newnode->prev = pdq->tail;
    

    if (empty(pdq))
        pdq->head = newnode;
    else
        pdq->tail->next = newnode;

    newnode->next = NULL;
    pdq->tail = newnode;

    (pdq->numofdata)++;
}
int pop_front(Deque* pdq)
{
    if (empty(pdq))
        return -1;
    Node* rnode = pdq->head;
    int rdata = rnode->data;

    pdq->head = rnode->next;
    free(rnode);

    (pdq->numofdata)--;

    if (empty(pdq))
        pdq->tail = NULL;
    else
        pdq->head->prev = NULL;

    return rdata;
}
int pop_back(Deque* pdq)
{
    if (empty(pdq))
        return -1;
    Node* rnode = pdq->tail;
    int rdata = rnode->data;

    pdq->tail = rnode->prev;
    free(rnode);

    (pdq->numofdata)--;

    if (empty(pdq))
        pdq->head = NULL;
    else
        pdq->tail->next = NULL;

    return rdata;
}

int front(Deque* pdq)
{
    if (empty(pdq))
        return -1;
    else
        return pdq->head->data;
}
int back(Deque* pdq)
{
    if (empty(pdq))
        return -1;
    else
        return pdq->tail->data;
}

int main()
{
    Deque deque;
    dequeinit(&deque);
    int n,i,t; 
    char str[101];
    scanf("%d", &n);
    for (i = 0; i < n; i++)
    {
        scanf("%s",str);
        if (strcmp(str, "push_front")==0)
        {
            scanf("%d", &t);
            push_front(&deque, t);
        }
        else if (strcmp(str, "push_back") == 0)
        {
            scanf("%d", &t);
            push_back(&deque, t);
        }
        else if (strcmp(str, "pop_front") == 0)
            printf("%d\n", pop_front(&deque));
        else if (strcmp(str, "pop_back") == 0)
            printf("%d\n", pop_back(&deque));
        else if (strcmp(str, "size") == 0)
            printf("%d\n", size(&deque));
        else if (strcmp(str, "empty") == 0)
            printf("%d\n", empty(&deque));
        else if (strcmp(str, "front") == 0)
            printf("%d\n", front(&deque));
        else if (strcmp(str, "back") == 0)
            printf("%d\n", back(&deque));

    }
    return 0;
}
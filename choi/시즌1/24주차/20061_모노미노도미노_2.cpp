#include <iostream>
#include <algorithm>
#include <vector>
#define fastio ios::sync_with_stdio(false);cin.tie(0);cout.tie(0);

using namespace std;

int N;
vector<vector<int>> blue, green;
vector<int> blueFloorSize, greenFloorSize;
vector<int> blueTopFloor, greenTopFloor;
int score, numTile;

void input(){
  cin >> N;
}

void init(){
  blue.assign(6, vector<int>(4, 0));
  green.assign(6, vector<int>(4, 0));
  blueFloorSize.assign(6, 0);
  greenFloorSize.assign(6, 0);
  blueTopFloor.assign(4, 5);
  greenTopFloor.assign(4, 5);
}

void arrangeBlocks(vector<vector<int>> &board, vector<int> &floorSize){
  int fall = 0;
  for (int r = 5; r >= 0; --r){
    if (board[r][0] == -1){
      floorSize[r] = 0;
      ++fall;
      for (int c = 0; c <= 3; ++c)
        board[r][c] = 0;
    }
    else if (fall){
      floorSize[r + fall] = floorSize[r];
      floorSize[r] = 0;
      for (int c = 0; c <= 3; ++c){
        board[r + fall][c] = board[r][c];
        board[r][c] = 0;
      }
    }
  }
}

void updateBoard(vector<vector<int>> &board, vector<int> &floorSize, vector<int> &ground){
  
  int numClear = 0;
  
  for (int r = 2; r <= 5; ++r){
    if (floorSize[r] == 4){
      ++numClear;
      numTile = numTile - 4;
      for (int c = 0; c <= 3; ++c)
        board[r][c] = -1;
    }
  }

  score += numClear;

  arrangeBlocks(board, floorSize);

  for (int r = 1; r >= 0; --r){
    if (floorSize[r]){
      ++numClear;
      for (int c = 0; c <= 3; ++c){
        if (board[r + 4][c])
          --numTile;
        board[r + 4][c] = -1;
      }
    }
  }

  arrangeBlocks(board, floorSize);

  for (int c = 0; c <= 3; ++c)
    ground[c] = min(5, ground[c] + numClear);
  for (int c = 0; c <= 3; ++c){
    for (int r = ground[c] + 1; r <= 5 && !board[r][c]; ground[c] = r++);
  }
}

void sendBlock(int pos, vector<vector<int>> &board, vector<int> &floorSize, vector<int> &ground){
  board[ground[pos]][pos] = 1;
  floorSize[ground[pos]]++;
  ground[pos]--;
}

void placeBlock(int t, int x, int y){
  if (t == 1){
    sendBlock(x, blue, blueFloorSize, blueTopFloor);
    sendBlock(y, green, greenFloorSize, greenTopFloor);
    numTile += 2;
  }
  else if (t == 2){ // horizontal
    sendBlock(x, blue, blueFloorSize, blueTopFloor);
    sendBlock(x, blue, blueFloorSize, blueTopFloor);
    greenTopFloor[y] = greenTopFloor[y + 1] = min(greenTopFloor[y], greenTopFloor[y + 1]);
    sendBlock(y, green, greenFloorSize, greenTopFloor);
    sendBlock(y + 1, green, greenFloorSize, greenTopFloor);
    numTile += 4;
  }
  else if (t == 3){ // vertical
    sendBlock(y, green, greenFloorSize, greenTopFloor);
    sendBlock(y, green, greenFloorSize, greenTopFloor);
    blueTopFloor[x] = blueTopFloor[x + 1] = min(blueTopFloor[x], blueTopFloor[x + 1]);
    sendBlock(x, blue, blueFloorSize, blueTopFloor);
    sendBlock(x + 1, blue, blueFloorSize, blueTopFloor);
    numTile += 4;
  }
  updateBoard(blue, blueFloorSize, blueTopFloor);
  // showBlue();
  updateBoard(green, greenFloorSize, greenTopFloor);
  // showGreen();
}

void solve(){
  init();
  int t, x, y;
  while (N--){
    cin >> t >> x >> y;
    placeBlock(t, x, y);
  }
  cout << score << '\n' << numTile;
}

int main(){
  fastio
  input();
  solve();
  return 0;
}
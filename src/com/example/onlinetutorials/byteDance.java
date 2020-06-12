/*
[
  [0, 0, 0, 0, 0, 0, 0, 0],
  [0, 0, 0, 1, 0, 0, 0, 0],
  [1, 1, 1, 1, 0, 0, 1, 0],
  [0, 1, 1, 0, 0, 1, 1, 0],
  [0, 1, 1, 0, 0, 0, 0, 0],
  [0, 0, 0, 0, 0, 0, 0, 0]
]

0 = ocean
1 = land
Adjacent pieces of land form islands
Adjacent = up down left right
Find out the area of the largest island
*/

public int findLargestIslan(int [][] input){
  int largestIsland = 0;
  for(int i = 0; i< input.length; i++){
    for(int j = 0; j< input.length; j++){
      int area = areaHelper(i, j, input);
      if(area > largestIsland){
        largestIsland = area;
      }
    }
  }
  return largestIsland;
}

public int areaHelper(int i, int j, int [][] arr){
  int area = 0;
  if(arr[i][j] == 1){
    area = 1;
    arr[i][j] = 0;
    area += areaHelper(i-1, j, arr);
    area += areaHelper(i, j-1, arr);
    area += areaHelper(i, j+1, arr);
    area += areahelper(i+1, j, arr);
  }
  return area;
}

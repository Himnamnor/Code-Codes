# 差分

## 一维差分

## 等差数列差分

### 问题描述

- 一开始1~n范围上的数字都是0，接下来一共有m个操作
- 每次操作：l~r上一次加上首项为s,末项e，公差d的数列

### 通用方法

```Java
void set(int l, int r, int s, int e, int d) {
    arr[l] += s;
    arr[l + 1] += d - s;
    arr[r + 1] -= d + e;
    arr[r + 2] += e;
}

void build() {
    for (int i = 1; i <= n; i++) {
        arr[i] = arr[i - 1] + arr[i];
    }
    for (int i = 0; i <= n; i++) {
        arr[i] += arr[i - 1];
    }
}
```

## 二维差分

- 和数组`sum[i][j]=sum[i-1][j]+sum[i][j-1]-sum [i-1][j-1];`
- 差分数组要开`n+2`行`m+2`列，从1开始到n+1结束

### 打标记

```java
void add(int x1, int y1, int x2, int y2, int v) {
    arr[x1][y1] += v;
    arr[x2 + 1][y2 + 1] += v;
    arr[x1][y2 + 1] -= v;
    arr[x2 + 1][y1] -= v;
}
```

### 计算和

```java
void build() {
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            arr[i][j] += arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1];
        }
    }
}
```

### leetcode lcp74涉及离散化方法，节省空间
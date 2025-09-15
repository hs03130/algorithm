#include <bits/stdc++.h>
using namespace std;

int coinCnt, targetPrice;
int coins[11];
int ans = 0;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> coinCnt >> targetPrice;
	for (int idx = 0; idx < coinCnt; idx++) {
		cin >> coins[idx]; // 금액은 오름차순으로 입력됨
	}
	for (int idx = coinCnt - 1; idx >= 0; idx--) {
		ans += targetPrice / coins[idx];
		targetPrice %= coins[idx];
	}

	cout << ans;
}
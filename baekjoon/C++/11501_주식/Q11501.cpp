#include <bits/stdc++.h>
using namespace std;

int testCase;
int day;
int price[1000001];
long long ans;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> testCase;
	while (testCase--) {
		cin >> day;
		for (int idx = 0; idx < day; idx++) cin >> price[idx];

		ans = 0;
		int maxPrice = price[day - 1];
		for (int idx = day - 2; idx >= 0; idx--) {
			if (price[idx] > maxPrice) maxPrice = price[idx];
			else ans += maxPrice - price[idx];
		}
		cout << ans << '\n';
	}
}
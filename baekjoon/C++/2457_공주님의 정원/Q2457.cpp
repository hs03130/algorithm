#include <bits/stdc++.h>
using namespace std;

#define BLOOM first
#define FALL second

int N;
vector<pair<int, int>> flowers;
int ans = 0;

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	for (int idx = 0; idx < N; idx++) {
		int sm, sd, em, ed;
		cin >> sm >> sd >> em >> ed;
		flowers.push_back({ sm * 100 + sd, em * 100 + ed });
	}
	flowers.push_back({ 1301,1302 });
	sort(flowers.begin(), flowers.end());

	int least = 301, lastFall = 0;
	for (int i = 0; i < N; i++) {
		// 11월 30일 까지 확인 완료
		if (flowers[i].BLOOM >= 1201 || least >= 1201) break;

		// 중간에 끊김
		if (flowers[i].BLOOM > least) break;

		lastFall = flowers[i].FALL;
		if (lastFall >= 1201 || flowers[i + 1].BLOOM > least) {
			ans++;
			least = lastFall;
		}
	}

	if (least < 1201) ans = 0;
	cout << ans;
}
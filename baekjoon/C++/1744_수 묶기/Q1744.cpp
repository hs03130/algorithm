#include <bits/stdc++.h>
using namespace std;

int N;
vector<int> numsN, numsP;
int ans = 0;

void sum(vector<int> v) {
	while (v.size() > 1) {
		ans += *(v.end() - 1) * *(v.end() - 2);
		v.pop_back(); v.pop_back();
	}

	if (v.size()) {
		ans += v[0];
	}
}

int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> N;
	int num;
	for (int idx = 0; idx < N; idx++) {
		cin >> num;
		if (num < 1) numsN.push_back(num);
		else if (num == 1) ans++;
		else numsP.push_back(num);
	}

	sort(numsN.begin(), numsN.end(), greater<>());
	sort(numsP.begin(), numsP.end());

	sum(numsN);
	sum(numsP);

	cout << ans;
}
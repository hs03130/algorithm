#include <bits/stdc++.h>
using namespace std;

#define TIME first
#define CNT second

int lessonCnt;
vector<pair<int, int>> lessons;
int ans;


int main(void) {
	ios::sync_with_stdio(0);
	cin.tie(0);

	cin >> lessonCnt;
	int start, end;
	for(int idx=0; idx< lessonCnt; idx++) {
		cin >> start >> end;
		lessons.push_back({ start, 1 });
		lessons.push_back({ end, -1 });
	}

	sort(lessons.begin(), lessons.end());

	int time = lessons[0].TIME;
	int cnt = 0;
	for (int idx = 0; idx < lessons.size(); idx++) {
		cnt += lessons[idx].CNT;
		ans = max(ans, cnt);
		time = lessons[idx].TIME;
	}
	cout << ans;
}
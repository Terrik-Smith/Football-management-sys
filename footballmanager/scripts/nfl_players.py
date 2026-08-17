import json
import os
import requests

BASE = "https://site.api.espn.com/apis/site/v2/sports/football/nfl"

print("Getting NFL teams...")

teams_response = requests.get(f"{BASE}/teams")
teams_response.raise_for_status()

teams = teams_response.json()["sports"][0]["leagues"][0]["teams"]

players = []

for team in teams:

    team_id = team["team"]["id"]
    team_name = team["team"]["displayName"]

    print(f"Loading {team_name}...")

    roster_url = f"{BASE}/teams/{team_id}/roster"

    roster = requests.get(roster_url)

    if roster.status_code != 200:
        print(f"Skipping {team_name}")
        continue

    roster_json = roster.json()

    for group in roster_json.get("athletes", []):

        for athlete in group.get("items", []):

            jersey = athlete.get("jersey")

            try:
                jersey = int(jersey)
            except:
                jersey = 0

            age = athlete.get("age")

            try:
                age = int(age)
            except:
                age = 0

            players.append({
                "playerName": athlete.get("displayName", ""),
                "position": athlete.get("position", {}).get("abbreviation", ""),
                "jerseyNumber": jersey,
                "age": age,
                "teamName": team_name
            })

os.makedirs("src/main/resources", exist_ok=True)

with open("src/main/resources/players.json", "w") as f:
    json.dump(players, f, indent=4)

print(f"\nCreated players.json with {len(players)} players!")
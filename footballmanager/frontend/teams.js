let allTeams = [];

fetch("http://localhost:8080/teams")
    .then(response => response.json())
    .then(teams => {

        allTeams = teams;
        displayTeams(allTeams);

    });

function displayTeams(teams) {

    const div = document.getElementById("teams");

    div.innerHTML = "";

    teams.forEach(team => {

        div.innerHTML += `

            <div class="team-card">

                <img
                    src="https://a.espncdn.com/i/teamlogos/nfl/500/${getLogo(team.teamName)}.png"
                    width="90"
                >

                <h2>${team.teamName}</h2>

                <p><strong>City:</strong> ${team.city}</p>

                <p><strong>Coach:</strong> ${team.coach}</p>

                <button onclick="viewRoster(${team.id}, '${team.teamName}')">
                    View Roster
                </button>

            </div>

        `;
    });

}

document.getElementById("search").addEventListener("keyup", function () {

    const search = this.value.toLowerCase();

    const filtered = allTeams.filter(team =>
        team.teamName.toLowerCase().includes(search)
    );

    displayTeams(filtered);

});

function viewRoster(id, teamName) {

    window.location =
        `players.html?teamId=${id}&teamName=${encodeURIComponent(teamName)}`;

}

function getLogo(teamName) {

    const logos = {

        "Arizona Cardinals":"ari",
        "Atlanta Falcons":"atl",
        "Baltimore Ravens":"bal",
        "Buffalo Bills":"buf",
        "Carolina Panthers":"car",
        "Chicago Bears":"chi",
        "Cincinnati Bengals":"cin",
        "Cleveland Browns":"cle",
        "Dallas Cowboys":"dal",
        "Denver Broncos":"den",
        "Detroit Lions":"det",
        "Green Bay Packers":"gb",
        "Houston Texans":"hou",
        "Indianapolis Colts":"ind",
        "Jacksonville Jaguars":"jax",
        "Kansas City Chiefs":"kc",
        "Las Vegas Raiders":"lv",
        "Los Angeles Chargers":"lac",
        "Los Angeles Rams":"lar",
        "Miami Dolphins":"mia",
        "Minnesota Vikings":"min",
        "New England Patriots":"ne",
        "New Orleans Saints":"no",
        "New York Giants":"nyg",
        "New York Jets":"nyj",
        "Philadelphia Eagles":"phi",
        "Pittsburgh Steelers":"pit",
        "San Francisco 49ers":"sf",
        "Seattle Seahawks":"sea",
        "Tampa Bay Buccaneers":"tb",
        "Tennessee Titans":"ten",
        "Washington Commanders":"wsh"

    };

    return logos[teamName];

}
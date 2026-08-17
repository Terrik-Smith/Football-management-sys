let teamCount = 0;
let playerCount = 0;


// Get teams
fetch("http://localhost:8080/teams")
    .then(response => response.json())
    .then(teams => {

        teamCount = teams.length;

        document.getElementById("teamCount").textContent =
            teamCount;

        document.getElementById("teamTotal").textContent =
            teamCount;

    })
    .catch(error => {

        console.error("Error loading teams:", error);

        document.getElementById("teamCount").textContent =
            "Error";

        document.getElementById("teamTotal").textContent =
            "Error";

    });


// Get players
fetch("http://localhost:8080/players")
    .then(response => response.json())
    .then(players => {

        playerCount = players.length;

        document.getElementById("playerCount").textContent =
            playerCount;

        document.getElementById("playerTotal").textContent =
            playerCount;

    })
    .catch(error => {

        console.error("Error loading players:", error);

        document.getElementById("playerCount").textContent =
            "Error";

        document.getElementById("playerTotal").textContent =
            "Error";

    });
let allPlayers = [];

const searchBox = document.getElementById("search");
const playersDiv = document.getElementById("players");
const status = document.getElementById("status");


fetch("http://localhost:8080/players")
    .then(response => {

        if (!response.ok) {
            throw new Error("Could not load players");
        }

        return response.json();

    })
    .then(players => {

        allPlayers = players;

        status.textContent =
            `Ready! ${allPlayers.length} players loaded. Type a name to search.`;

    })
    .catch(error => {

        console.error(error);

        status.textContent =
            "Unable to load players.";

    });


searchBox.addEventListener("input", function () {

    const searchText =
        searchBox.value.toLowerCase().trim();

    if (searchText === "") {

        playersDiv.innerHTML = "";

        status.textContent =
            `Ready! ${allPlayers.length} players loaded. Type a name to search.`;

        return;
    }

    const filteredPlayers = allPlayers.filter(player =>

        player.playerName &&
        player.playerName.toLowerCase().includes(searchText)

    );

    displayPlayers(filteredPlayers);

});


function displayPlayers(players) {

    playersDiv.innerHTML = "";

    if (players.length === 0) {

        status.textContent = "No players found.";

        return;
    }

    const results = players.slice(0, 50);

    status.textContent =
        `${players.length} player(s) found.`;

    results.forEach(player => {

        const teamName =
            player.teamName || "No team assigned";

        playersDiv.innerHTML += `

            <div class="player-card">

                <h2>${player.playerName}</h2>

                <h3>${teamName}</h3>

                <p>
                    <strong>Position:</strong>
                    ${player.position}
                </p>

                <p>
                    <strong>Jersey:</strong>
                    #${player.jerseyNumber}
                </p>

                <p>
                    <strong>Age:</strong>
                    ${player.age}
                </p>

            </div>

        `;

    });

    if (players.length > 50) {

        status.textContent =
            `${players.length} players found. Showing first 50.`;

    }

}
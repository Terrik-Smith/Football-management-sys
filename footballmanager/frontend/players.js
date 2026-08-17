const params = new URLSearchParams(window.location.search);

const teamId = params.get("teamId");
const teamName = params.get("teamName");

document.getElementById("teamTitle").innerHTML =
    `🏈 ${teamName}`;

fetch(`http://localhost:8080/teams/${teamId}/players`)
    .then(response => response.json())
    .then(players => {

        const div = document.getElementById("players");

        players.forEach(player => {

            div.innerHTML += `
                <div class="team-card">

                    <h2>${player.playerName}</h2>

                    <p><strong>Position:</strong> ${player.position}</p>

                    <p>Jersey #${player.jerseyNumber}</p>

                    <p>Age ${player.age}</p>

                </div>
            `;

        });

    });
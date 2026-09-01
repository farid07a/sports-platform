function renderPlayers(clubId) {

    const playersList =
        document.getElementById("players-list");

    playersList.innerHTML = "";

    const clubPlayers = players.filter(
        player => player.clubId === clubId
    );

    if (clubPlayers.length === 0) {

        playersList.innerHTML = `
            <p class="empty-message">
                No players found.
            </p>
        `;

        return;
    }

    clubPlayers.forEach(player => {

        const playerCard =
            document.createElement("div");

        playerCard.classList.add("player-card");

        playerCard.innerHTML = `
        <img
            src="${player.image}"
            alt="${player.firstName} ${player.lastName}"
            class="player-image"
        >

        <h3>
            ${player.firstName} ${player.lastName}
        </h3>

        <p>
            Position: ${player.position}
        </p>

        <p>
            Birth date: ${player.birthDate}
        </p>
        `;

        playersList.appendChild(playerCard);
    });
}

function renderMatches(clubId) {

    const matchesList =
        document.getElementById("matches-list");

    matchesList.innerHTML = "";

    const clubMatches = matches.filter(
        match => match.clubId === clubId
    );

    if (clubMatches.length === 0) {

        matchesList.innerHTML = `
            <p class="empty-message">
                No matches found.
            </p>
        `;

        return;
    }

    clubMatches.forEach(match => {

        const matchCard =
            document.createElement("div");

        matchCard.classList.add("match-card");

        matchCard.innerHTML = `
            <h3>vs ${match.opponent}</h3>

            <p>Date: ${match.date}</p>

            <p>Location: ${match.location}</p>

            <p>Status: ${match.result}</p>
        `;

        matchesList.appendChild(matchCard);
    });
}

function renderClubDetails() {

    const params =
        new URLSearchParams(window.location.search);

    const clubId =
        Number(params.get("id"));

    console.log("clubId:"+clubId);

    const selectedClub =
        clubs.find(item => item.id === clubId);

    console.log("SelectedClib:"+selectedClub);


    const detailsSection =
        document.getElementById("club-details");

    const playersSection =
        document.getElementById("club-players");

    const matchesSection =
        document.getElementById("club-matches");

    const notFound =
        document.getElementById("club-not-found");


    if (!selectedClub) {

        detailsSection.style.display = "none";
        playersSection.style.display = "none";
        matchesSection.style.display = "none";

        notFound.style.display = "block";

        return;
    }


    detailsSection.style.display = "block";
    playersSection.style.display = "block";
    matchesSection.style.display = "block";

    notFound.style.display = "none";


    const clubName =
        document.getElementById("club-name");


    const clubCity =
        document.getElementById("club-city");

    const clubSport =
        document.getElementById("club-sport");

    const clubImage =
        document.getElementById("club-image");

        console.log("get all element about club");
        console.log(clubName);
        console.log(clubCity);
        console.log(clubSport);
        console.log(clubImage);
        


    clubName.textContent =
        selectedClub.name;

    clubCity.textContent =
        selectedClub.city;

    clubSport.textContent =
        selectedClub.sport;

    clubImage.src =
        selectedClub.image;

    clubImage.alt =
        selectedClub.name;


    renderPlayers(clubId);

    renderMatches(clubId);
}


renderClubDetails();
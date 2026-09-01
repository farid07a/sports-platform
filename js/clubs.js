const clubGrid = document.querySelector(".clubs-grid");
const searchInput = document.getElementById("club-search");
const sportFilter = document.getElementById("sport-filter");
const noClubsMessage = document.getElementById("no-clubs-message");


function renderClubs(clubsToRender) {

    clubGrid.innerHTML = "";

    if (clubsToRender.length === 0) {
        noClubsMessage.style.display = "block";
        return;
    }

    noClubsMessage.style.display = "none";

    clubsToRender.forEach(item => {

        const card = document.createElement("article");

        card.classList.add("club-card");
        console.log("Image Path is:"+item.image)
        card.innerHTML = `
            <img
                src="${item.image}"
                alt="${item.name}"
                class="club-image"
            >

            <div class="club-card-content">

                <h2>${item.name}</h2>

                <p>City: ${item.city}</p>

                <p>Sport: ${item.sport}</p>

                <a href="club-details.html?id=${item.id}">
                    View Club
                </a>
            </div>
        `;
        
        clubGrid.appendChild(card);
    });
}


function applyFilters() {

    const searchText =
        searchInput.value.toLowerCase();

    const selectedSport =
        sportFilter.value;

    const filteredClubs = clubs.filter(item => {

        const matchesSearch =
            item.name
                .toLowerCase()
                .includes(searchText);

        const matchesSport =
            selectedSport === "all" ||
            item.sport.toLowerCase() === selectedSport;

        return matchesSearch && matchesSport;
    });

    renderClubs(filteredClubs);
}


renderClubs(clubs);

searchInput.addEventListener("input", applyFilters);

sportFilter.addEventListener("change", applyFilters);
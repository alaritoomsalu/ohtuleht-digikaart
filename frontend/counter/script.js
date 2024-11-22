const backendUrl = "https://ohtuleht-digikaart.onrender.com";

// Function to fetch the current counter value
async function fetchCounter() {
    try {
        const response = await fetch(`${backendUrl}/count-wish/counter`);
        if (!response.ok) {
            throw new Error("Failed to fetch counter.");
        }
        const data = await response.json();
        document.getElementById("counter").textContent = `Total Wishes: ${data.totalWishes}`;
    } catch (error) {
        console.error(error);
        document.getElementById("counter").textContent = "Error loading counter.";
    }
}

// Function to reset the counter
async function resetCounter() {
    document.getElementById("successMessage").textContent = "";
    document.getElementById("errorMessage").textContent = "";
    try {
        const response = await fetch(`${backendUrl}/count-wish/counter/reset`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
        });
        if (!response.ok) {
            throw new Error("Failed to reset counter.");
        }
        const data = await response.json();
        document.getElementById("successMessage").textContent = data.message;
        await fetchCounter(); // Refresh the counter display
    } catch (error) {
        console.error(error);
        document.getElementById("errorMessage").textContent = "Failed to reset counter.";
    }
}

// Attach event listener to the reset button
document.getElementById("resetButton").addEventListener("click", resetCounter);

// Load the counter on page load
fetchCounter();
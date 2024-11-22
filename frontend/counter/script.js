const apiUrl = "https://ohtuleht-digikaart.onrender.com/count-wish";

const counterView = document.getElementById("counter-view");
const counterElement = document.getElementById("counter");
const resetButton = document.getElementById("reset-button");
const confirmationDialog = document.getElementById("confirmation-dialog");
const confirmResetButton = document.getElementById("confirm-reset");
const cancelResetButton = document.getElementById("cancel-reset");

// Fetch the current counter value
async function fetchCounter() {
    try {
        const response = await fetch(`${apiUrl}/counter`);
        const data = await response.json();
        counterElement.textContent = data.totalWishes;
    } catch (error) {
        console.error("Failed to fetch counter:", error);
    }
}

// Show confirmation dialog
resetButton.addEventListener("click", () => {
    confirmationDialog.style.display = "block";
    counterView.style.display = "none"
});

// Handle reset confirmation
confirmResetButton.addEventListener("click", async () => {
    try {
        const response = await fetch(`${apiUrl}/counter/reset`, {
            method: "POST",
        });
        const data = await response.json();
        alert(data.message); // Display reset success message
        await fetchCounter(); // Update the counter
    } catch (error) {
        console.error("Failed to reset counter:", error);
    }
    confirmationDialog.style.display = "none";
    counterView.style.display = "block";
});

// Handle reset cancellation
cancelResetButton.addEventListener("click", () => {
    confirmationDialog.style.display = "none";
    counterView.style.display = "block";
});

// Fetch counter on page load
fetchCounter();
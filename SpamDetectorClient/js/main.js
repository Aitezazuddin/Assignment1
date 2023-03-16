// TODO: onload function should retrieve the data needed to populate the UI
// Get the table element by its ID
const table = document.getElementById("result-table");

// Make an AJAX request to get the data
fetch("/spamDetector-1.0/api/spam/results")
  .then(response => response.json())
  .then(data => {
    // Loop through the data and add a row to the table for each item
    data.forEach(item => {
      // Create a new row
      const row = table.insertRow(-1);

      // Add the filename column
      const filenameCell = row.insertCell(0);
      filenameCell.innerHTML = item.filename;

      // Add the spam percentage column
      const spamCell = row.insertCell(1);
      spamCell.innerHTML = item.spamPercentage.toFixed(2);

      // Add the actual category column
      const actualCell = row.insertCell(2);
      actualCell.innerHTML = item.actualCategory;
    });
  })
  .catch(error => console.error(error));

  




const express = require("express")
const app = express()
const port = 80

app.get('/', (req, res) => res.send(`
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <title>webapp</title>
    <style>
    form {
      width:90%;
      text-align:right;
    }
    table, th, td {
      border: 1px solid black;
      border-collapse: collapse;
    }
    </style>
  </head>
  <body>
    <div class="row">
      <div class="col-md-4">
        <form id="currentVal">
          <label for="cVesselNumber">Current Vessel Number</label>
          <input type="text" id="cVesselNumber" name="cVesselNumber"><br>
 
          <label for="cBadgeNumber">Current Badge Number</label>
          <input type="text" id="cBadgeNumber" name="cBadgeNumber"><br>

          <label for="craneNumber">Crane Number</label>
          <input type="text" id="craneNumber" name="craneNumber"><br>

          <input type="Submit" value="Show Current Records">
        </form>
      </div>

      <div class="col-md-4">
        <form id="dateTimes">
          <label for="dates">Date</label>
          <div id="dates">
            <label for="dateBeginning">Beginning</label>
            <input type="text" id="dateBeginning" name="dateBeginning"><br>

            <label for="dateEnd">End</label>
            <input type="text" id="dateEnd" name="dateEnd"><br>
          </div>
        </form>
      </div>

      <div class="col-md-4">
        <form id="newVals">
          <label for="nVesselNumber">New Vessel Number</label>
          <input type="text" id="nVesselNumber" name="nVesselNumber"><br>
 
          <label for="nBadgeNumber">New Badge Number</label>
          <input type="text" id="nBadgeNumber" name="nBadgeNumber"><br>

          <input type="Submit" value="Test Current Records"><br>
        </form>
      </div>
    </div>

    <table style="width:100%">
      <tr>
        <th>Crane_Number</th>
        <th>Badge_Number</th>
        <th>Ship_Number</th>
        <th>Datetime</th>
      </tr>
      <tr>
        <td>3039</td>
        <td>8038</td>
        <td>9879</td>
        <td>2/13/2020 3:15:44 PM</td>
      </tr>
    </table>
  </body>
</html>
`))

app.listen(port, () => console.log(`server started on localhost:${port}`))
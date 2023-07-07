<%@ page import="org.example.*" %>
<html xmlns="http://www.w3.org/1999/html">
<head>
    <%@include file="js/showTable.js" %>
    <link rel="stylesheet" href="css/styles.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Montserrat:wght@400;700&display=swap">
</head>
<body>
<img src="https://image.freepik.com/free-vector/elegant-dark-background-with-golden-details_52683-33648.jpg" class="background-image">

<div class="rectangle">
    Earn % Compare
</div>
<div class="input-container">
    <form action="/compare" method="post">
        <input type="text" name="amount" placeholder="Enter amount" />
        <select id="durList" name="crypto">
          <option value="BTC">Bitcoin</option>
          <option value="ETH">Ethereum</option>
          <option value="USDT">Tether</option>
          <option value="USDC">USD Coin</option>
          <option value="DAI">Dai</option>
          <option value="PAXG">Pax Gold</option>
          <option value="ALGO">Algorand</option>
          <option value="AVAX">Avalanche</option>
          <option value="BNB">Binance Coin</option>
          <option value="ADA">Cardano</option>
          <option value="CELR">Celer Network</option>
          <option value="ATOM">Cosmos</option>
          <option value="EGLD">Elrond</option>
          <option value="FTM">Fantom</option>
          <option value="ONE">Harmony</option>
          <option value="NEAR">NEAR Protocol</option>
          <option value="DOT">Polkadot</option>
          <option value="MATIC">Polygon</option>
          <option value="SOL">Solana</option>
          <option value="VET">VeChain</option>
          <option value="ZIL">Zilliqa</option>
        </select>

        <select id="durList" name="duration">
          <option value="1">1 Month</option>
          <option value="3">3 Months</option>
          <option value="Flexible">Flexible</option>
        </select>
        <select id="durList" name="lock">
                      <option value="Less">Less</option>
                      <option value="Normal">Normal</option>
                      <option value="More">More</option>
                    </select>
        <div class="info-icon" onmouseover="showTooltip()" onmouseout="hideTooltip()">
          <span class="tooltip">CRO Lockup:<br>Less - Less than 4000<br>Normal - 4000 to 40000<br>More - 40000 or more</span>
          i
        </div>
        <input type="submit" id= "#compare" name="compare" value="Compare" />
    </form>
</div>
<div class="crypto-info">
    <span class="crypto-name">Crypto: ${crypto}</span>
    <span class="crypto-value">Value: ${mValue}</span>
    <span class="crypto-amount">Amount: ${amount}</span>
</div>

<div class="table-container">
    <table id="myTable">
        <tr>
            <th id="cellId"> </th>
            <th>Binance</th>
            <th>CRO</th>
        </tr>
        <tr>
            <th>APR</th>
            <td>${APRBinance}</td>
            <td>${APRCRO}</td>
        </tr>
        <tr>
            <th>Annual Value($)</th>
            <td>${anualBinance}</td>
            <td>${AnualCRO}</td>
        </tr>
        <tr>
            <th>Annual Value(Crypto)</th>
            <td>${anualCryptoB}</td>
            <td>${anualCryptoCRO}</td>
        </tr>
        <tr>
            <th>Weekly Value($)</th>
            <td>${semanalBinance}</td>
            <td>${SemanalCRO}</td>
        </tr>
    </table>
</body>
</html>
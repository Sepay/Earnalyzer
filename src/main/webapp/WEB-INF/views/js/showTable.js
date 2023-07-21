<script>
    function showTooltip() {
      const tooltip = document.querySelector('.tooltip');
      tooltip.style.visibility = 'visible';
    }

    function hideTooltip() {
      const tooltip = document.querySelector('.tooltip');
      tooltip.style.visibility = 'hidden';
    }
    function toggleElements() {
        var cryptoInfo = document.querySelector('.crypto-info');
        var tableContainer = document.querySelector('.table-container');

        if (cryptoInfo.style.display === 'none') {
            cryptoInfo.style.display = 'block';
            tableContainer.style.display = 'block';
        } else {
            cryptoInfo.style.display = 'none';
            tableContainer.style.display = 'none';
        }
    }
</script>

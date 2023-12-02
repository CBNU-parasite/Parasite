const caloryGraph = document.getElementById('calGraph');
const pieChart = document.getElementById('pieChart');

new Chart(caloryGraph, {
    type: 'line',
    data: {
      labels: ['2023.01.01', '2023.01.02', '2023.01.03', '2023.01.04', '2023.01.05', '2023.01.06'],
      datasets: [{
        label: 'Daily Calory',
        data: [2100, 2600, 2300, 2500, 2000, 1700],
        borderWidth: 1
      }]
    },
    options: {
      scales: {
        y: {
          beginAtZero: true
        }
      }
    }
  }
);

new Chart(ctx, {
  type: 'bar',
  data: {
    labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
    datasets: [{
      label: '# of Votes',
      data: [12, 19, 3, 5, 2, 3],
      borderWidth: 1
    }]
  },
  options: {
    scales: {
      y: {
        beginAtZero: true
      }
    }
  }
});
const caloryGraph = document.getElementById('calGraph');
const pieChart = document.getElementById('pieChart');
const stickGraph = document.getElementById('detailed')

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

new Chart(pieChart, {
  type: 'pie',
  data: {
    labels: ['carbohydrate', 'protein', 'fat'],
    datasets:[{
      data:[70,20,10],
      backgroundColor: [
        'rgb(255, 99, 132)',
        'rgb(54, 162, 235)',
        'rgb(255, 205, 86)'
      ],
      hoverOffset: 4
    }]
  }
});

new Chart(stickGraph, {
  type: 'bar',
  data: {
    labels: ['열량(kcal)', '당류', '나트륨', '콜레스테롤', '포화지방산', '트랜스지방'],
    datasets: [{
      label: '권장량(%)',
      data: [90, 40, 100, 80, 20, 30],
      borderWidth: 1,
      backgroundColor: [
        'rgba(255, 99, 132, 0.2)',
        'rgba(255, 159, 64, 0.2)',
        'rgba(255, 205, 86, 0.2)',
        'rgba(75, 192, 192, 0.2)',
        'rgba(54, 162, 235, 0.2)',
        'rgba(153, 102, 255, 0.2)',
        'rgba(201, 203, 207, 0.2)'
      ]
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
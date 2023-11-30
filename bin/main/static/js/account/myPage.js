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

new Chart(pieChart,{
    type: 'pie',
    weight:11,
    hoverBorderJoinStyle : 'round',
    data:{
        labels:['탄수화물','단백질','지방'],
        datasets:[{data: [70,10,20],
            backgroundColor: [
                'rgb(255, 99, 132)',
                'rgb(54, 162, 235)',
                'rgb(255, 205, 86)'
            ],
            hoverOffset: 4
    }]
    }
  }
)
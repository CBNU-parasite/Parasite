const pieChart = document.getElementById('total');

new Chart(pieChart,{
    type: 'pie',
    weight:1100,
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
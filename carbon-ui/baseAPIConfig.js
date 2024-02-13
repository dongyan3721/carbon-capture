const NOW_ENVIRONMENT = 'develop'

// 访问后台静态资源，部署上线之后可能需要切换访问基路径
const baseStaticRecourseAPI = {
    develop: "http://localhost:80",
    application: '/'
}

export {NOW_ENVIRONMENT, baseStaticRecourseAPI}
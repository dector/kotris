package kotris

import org.lwjgl.glfw.GLFW.*
import org.lwjgl.glfw.GLFWErrorCallback
import org.lwjgl.glfw.GLFWKeyCallback
import org.lwjgl.opengl.GL
import org.lwjgl.system.MemoryUtil.NULL

class App {

    val errorCallback = GLFWErrorCallback.createPrint(System.out)

    var window: Long = 0

    val keyCallback = object : GLFWKeyCallback() {

        override fun invoke(p0: kotlin.Long, key: kotlin.Int, p2: kotlin.Int, action: kotlin.Int, p4: kotlin.Int) {
            if (key == GLFW_KEY_ESCAPE && action == GLFW_PRESS)
                glfwSetWindowShouldClose(window, GLFW_TRUE)
        }
    }

    fun main() {
        init()
        loop()
        release()
    }

    fun init() {
        glfwSetErrorCallback(errorCallback)

        if (glfwInit() != GLFW_TRUE) {
            throw IllegalStateException("GLFW not initialized")
        }

        window = glfwCreateWindow(640, 480, "Kotris", NULL, NULL)
        if (window == NULL) {
            glfwTerminate()
            throw RuntimeException("GLFW window not created")
        }

        glfwSetKeyCallback(window, keyCallback)

        glfwMakeContextCurrent(window)
        GL.createCapabilities()
    }

    fun loop() {
        while (glfwWindowShouldClose(window) != GLFW_TRUE) {
            val time = glfwGetTime()

            glfwSwapBuffers(window)
            glfwPollEvents()
        }
    }

    fun release() {
        glfwDestroyWindow(window)
        keyCallback.release()

        glfwTerminate()

        errorCallback.release()
    }
}

fun main(args: Array<String>) {
    App().main()
}
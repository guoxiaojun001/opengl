uniform mat4 mMVPMatrix;//声明一个全局4x4的总变换矩阵
attribute vec3 aPosition;//顶点位置
attribute vec4 aColor;//顶点颜色
varying vec4 vColor;//传给片元着色器的颜色值
void main(){
	gl_Position=mMVPMatrix*vec4(aPosition,1);//指定顶点位置
	vColor=aColor;
}
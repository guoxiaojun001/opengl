uniform mat4 mMVPMatrix;//����һ��ȫ��4x4���ܱ任����
attribute vec3 aPosition;//����λ��
attribute vec4 aColor;//������ɫ
varying vec4 vColor;//����ƬԪ��ɫ������ɫֵ
void main(){
	gl_Position=mMVPMatrix*vec4(aPosition,1);//ָ������λ��
	vColor=aColor;
}
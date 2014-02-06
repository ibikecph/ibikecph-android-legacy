Building:
	IbikeCPHLib references the osmdroid-lib fork and Facebook SKD 3.0.1 and they are in the same repository as the IbikeCPHLib. After importing the projects in the workspace, go to Project 
	properties -> Android , and in the Libraries view, check if the project references are ok, and if not, remove the references, and add new ones.
	If there is an error where the support jars don't match, copy the android-support-v4.jar from IbikeCPHLib/libs into osmdroid-lib and FacebookSDK 
	libs folders.